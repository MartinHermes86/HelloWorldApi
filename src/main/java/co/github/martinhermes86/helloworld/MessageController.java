package co.github.martinhermes86.helloworld;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final List<Message> messages = new ArrayList<>();

    public MessageController() {
        messages.add(new Message("1", "Martin", "Hello, World!"));
        messages.add(new Message("2", "Martin", "Hello, Java!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message greet(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Message with ID " + id + " not found."));
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable String id, @RequestBody Message message) {
        Message existingMessage = getMessage(id);
        existingMessage.setName(message.getName());
        existingMessage.setMessage(message.getMessage());
        return existingMessage;
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable String id) {
        boolean removed = messages.removeIf(message -> message.getId().equals(id));
        if (removed) {
            return "Message with ID " + id + " removed successfully.";
        }
        return "Message with ID " + id + " not found.";
    }
}

