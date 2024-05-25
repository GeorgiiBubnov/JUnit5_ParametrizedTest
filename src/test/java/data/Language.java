package data;

public enum Language {
    RU("Делимся нашей экспертизой и опытом создания крутых игр!"),
    EN("We share our expertise and experience in creating cool games! "),
    HY("Մենք կիսում ենք մեր փորձաքննությունը և զվարճալի խաղեր ստեղծելու փորձը!");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
