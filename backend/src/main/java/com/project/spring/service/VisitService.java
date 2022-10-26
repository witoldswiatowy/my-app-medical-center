package com.project.spring.service;

import com.project.spring.model.dto.BookingVisitRequest;
import com.project.spring.model.dto.VisitDto;

import java.util.List;

public interface VisitService {

    List<VisitDto> getVisitsList();

//    List<VisitDto> getVisitsList(Long userId);

    /**
     * Persists the passed visit.
     * If the visit has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param bookingVisitRequest - params of visit to create
     * @return created {@link VisitDto}
     */
    VisitDto bookingVisit(BookingVisitRequest bookingVisitRequest);
}
