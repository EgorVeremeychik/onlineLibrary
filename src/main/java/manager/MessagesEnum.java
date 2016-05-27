package manager;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */
public enum MessagesEnum {
    SUCCESS("param.success"),
    ERROR_USER_LOGIN("error.user.login"),
    ERROR_USER_REGISTRATION("error.user.registration");

    String value;

    MessagesEnum(String value){
        this.value = value;
    }

    public String getValue(){return this.value;}
}

