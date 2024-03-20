package com.mtaerohand.duelsupportspring.Services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Repositories.ModeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModeService {
    private final ModeRepository modeRepository;

    public List<Mode> getModes() {
        return modeRepository.findAll(Sort.by(Sort.Direction.DESC, "isPermanent"));
    }

    public List<Mode> getModesOngoing() {
        return modeRepository.findAllModesOngoing();
    }
}
