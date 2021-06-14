package com.example.qrcodeassembler.backend.service;

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

    public void updateExistsMarkOrInsertNewMark(List<Mark> marks) {
        List<Mark> markForUpdate = new ArrayList<>();
        for (Mark mark: marks) {
            Optional<Mark> optionalMark = markRepository.findByCis(mark.getCis());
            Mark changesMark;
            if (optionalMark.isPresent()) {
                changesMark = optionalMark.get();
                changesMark.updateMark(mark);
            }
            else {
                changesMark = new Mark(mark.getCis(), mark.getBarcode(), mark.getNumberBox(), mark.getNumberOrder(), mark.getDate());
            }
            markForUpdate.add(changesMark);
        }
        markRepository.saveAll(markForUpdate);
    }

    public void insertMark(List<Mark> marks) {
        markRepository.saveAll(marks);
    }
}
