package com.mtaerohand.duelsupportspring.Controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("spreadsheet")
@Validated
@CrossOrigin("*")
public class SpreadSheetController {

}
