package Train.Ticket.Train.Ticket.service;


import Train.Ticket.Train.Ticket.dao.model.*;
import Train.Ticket.Train.Ticket.dao.repository.TrainRepository;
import Train.Ticket.Train.Ticket.dto.response.FindTrainScheduleResponse;
import Train.Ticket.Train.Ticket.exception.TrainCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements  TrainService {
    private final TrainRepository trainRepository;
    private final ScheduleService scheduleService;



    @Transactional
    public TrainResponse addTrain(TrainRequest trainRequest) {
        // First, save the Train
        Train trainToSave = Train.builder()
                .trainType(trainRequest.getTrain().getTrainType())
                .trainName(trainRequest.getTrain().getTrainName())
                .build();

        Train savedTrain = trainRepository.save(trainToSave);  // Save train first

        // Now, save the TrainClasses with reference to the saved Train
        List<TrainClass> trainClasses = new ArrayList<>();
        for (TrainClassRequest request : trainRequest.getTrainClassRequests()) {
            TrainClass trainClass = new TrainClass();
            trainClass.setClassName(request.getClassName());
            trainClass.setPrice(request.getPrice());
            trainClass.setTrain(savedTrain);  // Associate train with the train class
            trainClasses.add(trainClass);
        }

        List<TrainClass> savedTrainClasses = trainClassRepository.saveAll(trainClasses);

        // Generate seats for each TrainClass
        for (int i = 0; i < savedTrainClasses.size(); i++) {
            TrainClass trainClass = savedTrainClasses.get(i);
            TrainClassRequest request = trainRequest.getTrainClassRequests().get(i);
            seatService.generateSeatsForTrainClass(trainClass, request.getStartSeatNumber(), request.getEndSeatNumber());
        }

        // Prepare the response
        List<TrainClassResponse> trainClassResponses = savedTrainClasses.stream()
                .map(trainClass -> {
                    List<SeatResponse> seatResponses = trainClass.getSeats().stream()
                            .map(seat -> new SeatResponse(seat.getSeatId(), seat.getSeatNumber(), seat.getSeatStatus().name()))
                            .collect(Collectors.toList());
                    return new TrainClassResponse(trainClass.getId(), trainClass.getClassName(), trainClass.getPrice(), seatResponses);
                })
                .collect(Collectors.toList());

        // Return the train with its classes and generated seats
        return new TrainResponse(savedTrain.getTrainId(), savedTrain.getTrainType(), savedTrain.getTrainName(), trainClassResponses);
    }

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





