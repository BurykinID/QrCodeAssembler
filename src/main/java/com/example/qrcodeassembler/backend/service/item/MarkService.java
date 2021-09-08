package com.example.qrcodeassembler.backend.service.item;

import com.example.qrcodeassembler.backend.dto.assembledContainerAndBox.MarkDto;
import com.example.qrcodeassembler.backend.entity.Mark;
import com.example.qrcodeassembler.backend.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<MarkDto> findAllAssembledMarkSinceDate(Date date) {
        List<MarkDto> markDtoList = new ArrayList<>();
        List<Mark> marks = markRepository.findByDate(date);
        if (marks.size() > 0) {
            for (Mark mark: marks) {
                markDtoList.add(new MarkDto(mark));
            }
        }
        return markDtoList;
    }

    public void updateMark(List<Mark> marks) {
        List<Mark> markForUpdate = new ArrayList<>();
        for (Mark mark: marks) {
            Optional<Mark> markFromDataBase = markRepository.findByCis(mark.getCis());
            markFromDataBase.ifPresent(
                    updateMark -> {
                        updateMark.updateMark(mark);
                        markForUpdate.add(updateMark);
                    }
            );
        }
        markRepository.saveAll(markForUpdate);
    }

    public void insertMark(List<Mark> marks) {
        markRepository.saveAll(marks);
    }

    public boolean isMarkInDataBase(Mark mark) {
        return markRepository.findByCis(mark.getCis()).isPresent();
    }
}
