package com.gateway.notification.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@lombok.Data
public class Data {
    // dto to hold all the notification relevant info - can hold upto 10 kb of data.
}
