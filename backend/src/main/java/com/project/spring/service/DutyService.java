package com.project.spring.service;

import com.project.spring.model.dto.AddDutyRequest;
import com.project.spring.model.dto.DutyDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

public interface DutyService {

    /**
     * Method to get all duties.
     *
     * @return found {@link Set < DutyDto >} of all duties
     */
    List<DutyDto> getDutiesList();

    /**
     * Persists the passed duty.
     * If the duty has already DB ID assigned, then the implementation might throw an {@link IllegalArgumentException}.
     *
     * @param request - params of duty to create
     * @return created {@link DutyDto}
     */
    DutyDto addDuty(Long userId, AddDutyRequest request);

    /**
     * Delete the passed duty.
     * If the duty does not exist in DB, then the implementation might throw an {@link EntityNotFoundException}.
     *
     * @param dutyId - id of duty to delete
     */
    void deleteDuty(Long dutyId);
}
