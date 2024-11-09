package com.example.mail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Модель события, представляющая информацию о событии, связанном с электронной почтой.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    /**
     * Адрес электронной почты, связанный с событием.
     */
    private String email;

    /**
     * Описание события.
     */
    private String description;
}
