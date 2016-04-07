package hello;

import java.util.UUID;

public class GreetingEntity{

    private final String content;
    private final String id = UUID.randomUUID().toString();

    public GreetingEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }
}
