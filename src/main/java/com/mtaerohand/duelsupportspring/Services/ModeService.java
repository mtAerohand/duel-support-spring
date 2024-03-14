package com.mtaerohand.duelsupportspring.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mtaerohand.duelsupportspring.Entities.Mode;
import com.mtaerohand.duelsupportspring.Entities.ModeDetail;
import com.mtaerohand.duelsupportspring.Repositories.ModeDetailRepository;
import com.mtaerohand.duelsupportspring.Repositories.ModeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModeService {
    private final ModeRepository modeRepository;
    private final ModeDetailRepository modeDetailRepository;

    public List<Mode> getAllModes() {
        return modeRepository.findAll(Sort.by(Sort.Direction.DESC, "isPermanent"));
    }

    public List<ModeDetail> getModeDetailsOngoing(Integer modeId) {
        return modeDetailRepository.findAllOngoingByModeId(modeId, LocalDateTime.now());
    }
}
