package com.tasks.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Task {
    private int id;
    private String text;
    private boolean isDone;
}
