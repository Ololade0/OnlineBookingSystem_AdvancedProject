package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.dao.model.*;
import Train.Ticket.Train.Ticket.dao.repository.TrainRepository;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;
import Train.Ticket.Train.Ticket.exception.TrainCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements  TrainService {
    private final TrainRepository trainRepository;
    private final ScheduleService scheduleService;



    public Train saveTrain(Train train) {
        if (train == null) {
            throw new IllegalArgumentException("Train cannot be null");
        }
        if (train.getTrainClasses() == null) {
            train.setTrainClasses(new HashSet<>());
        }
        int totalSeat = 0;
        List<TrainClass> trainClassesCopy = new ArrayList<>(train.getTrainClasses());
        for (TrainClass trainClass : trainClassesCopy) {
            if (trainClass == null) {
                throw new IllegalArgumentException("TrainClass cannot be null");
            }
            if (trainClass.getTrain() != null && !trainClass.getTrain().equals(train)) {
                throw new IllegalStateException("TrainClass is already associated with another Train");
            }

            // Generate and set seats based on class type
            int startSeatNumber = trainClass.getStartSeatNumber();
            int endSeatNumber = trainClass.getEndSeatNumber();
            List<Seat> seats = generateSeatsForTrainClass(trainClass, startSeatNumber, endSeatNumber);
            trainClass.setSeats(seats);

            train.addTrainClass(trainClass);
            totalSeat += seats.size();
        }

        train.setTrainTotalSeat(totalSeat);
        return trainRepository.save(train);
    }


    ;
    public List<Seat> generateSeatsForTrainClass(TrainClass trainClass, int startSeatNumber, int endSeatNumber) {
        List<Seat> seats = new ArrayList<>();
        for (int i = startSeatNumber; i <= endSeatNumber; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setSeatOccupied(SeatStatus.AVAILABLE);
            seat.setTrainClass(trainClass);
            seats.add(seat);
        }
        return seats;
    }



    @Override
    public Train createScheduleForTrain(Long trainId, Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        Train foundTrain = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train with ID " + trainId + " cannot be found"));
        foundTrain.getScheduleList().add(createdSchedule);
        createdSchedule.setTrainSchedule(foundTrain);
        return trainRepository.save(foundTrain);
    }


    @Override
    public FindTrainScheduleResponse findCreatedSchedule(Long trainId, Long scheduleId) {
        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
        Optional<Schedule> foundSchedule = scheduleService.findSchedule(scheduleId);
        if (foundTrain.isPresent()) {
            if (foundSchedule.isPresent()) {
                return new FindTrainScheduleResponse(foundTrain.get());
            }
            throw new RuntimeException("Schedule cannot be found");

        }

        throw new RuntimeException("Train cannot be found");
    }

    @Override
    public Train getTrain(Long trainId) {
        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
        if (foundTrain.isPresent()) {
            foundTrain.get().getTrainClasses();
            return foundTrain.get();
        }
        throw new TrainCannotBeFoundException("Train Cannot be found");
    }

//    @Override
//    public String updateTrain(Train updateTrain, Long trainId) {
//        Optional<Train> foundTrain = trainRepository.findByTrainId(trainId);
//        if (foundTrain.isPresent()) {
//            Train trainToUpdate = foundTrain.get();
//            trainToUpdate.setTrainName(updateTrain.getTrainName());
//            trainToUpdate.setTrainType(updateTrain.getTrainType());
//
//            List<TrainClass> trainClasses = updateTrain.getTrainClasses();
//            for (TrainClass trainClass : trainClasses) {
//                trainClass.setTrainclass(trainToUpdate);
//
//            }
//
//            trainToUpdate.setTrainClasses(trainClasses);
//            trainRepository.save(trainToUpdate);
//
//            return "Train successfully updated";
//        } else {
//            throw new TrainCannotBeFoundException("Train cannot Be found");
//        }
    }





