package com.pankaj.redisexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails implements Serializable {
    private Date timestamp;
    private String message;
    private String details;
}
