package domain.utils;

public class EntityAlreadyExist extends RuntimeException{
    public EntityAlreadyExist(String message) {
        super(message);
    }
}
