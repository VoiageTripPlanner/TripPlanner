package com.project.demo.logic;

import com.project.demo.entity.Lodge;
import com.project.demo.logic.exceptions.LodgeServiceException;
import com.project.demo.repository.LodgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LodgeService implements IService<Lodge,Integer>{

    @Autowired
    private LodgeRepository lodgeRepository;

    @Override
    public Lodge save(Lodge entity) {
        try {
            return lodgeRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new LodgeServiceException(
                    "Failed to save lodge: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The lodge entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new LodgeServiceException(
                    "Failed to save lodge: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The lodge entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new LodgeServiceException(
                    "Failed to save lodge: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while saving the lodge. Please try again later.",
                    e
            );
        }
    }

    @Override
    public List<Lodge> findAll() {
        try {
            return lodgeRepository.findAll();
        } catch (Exception e) {
            throw new LodgeServiceException(
                    "Failed to retrieve all lodges.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while retrieving lodges. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Lodge findById(Integer id) {
        try {
            return lodgeRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new LodgeServiceException(
                    "Failed to find lodge by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while finding the lodge. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Lodge update(Lodge entity) {
        try {
            return lodgeRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new LodgeServiceException(
                    "Failed to update lodge: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The lodge entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new LodgeServiceException(
                    "Failed to update lodge: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The lodge entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new LodgeServiceException(
                    "Failed to update lodge: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while updating the lodge. Please try again later.",
                    e
            );
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            lodgeRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new LodgeServiceException(
                    "Failed to delete lodge by ID: invalid ID.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The lodge ID provided is invalid. Please provide a valid ID.",
                    e
            );
        } catch (Exception e) {
            throw new LodgeServiceException(
                    "Failed to delete lodge by ID: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while deleting the lodge. Please try again later.",
                    e
            );
        }
    }

}
