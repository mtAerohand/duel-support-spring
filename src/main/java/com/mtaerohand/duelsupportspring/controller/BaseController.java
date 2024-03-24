package com.mtaerohand.duelsupportspring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// v1はapplication.propertiesで管理したい
/**
 * 基本コントローラ
 */
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class BaseController {

}
