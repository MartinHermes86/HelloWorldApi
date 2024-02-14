package co.github.martinhermes86.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    private String id;
    private String name;
    private String message;
}
