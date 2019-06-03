package custom_enum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TreeItems {

    ITEM1("Меню событий"),
    ITEM2("Меню студентов"),
    ITEM3("Меню компаний"),
    ITEM4("Список контактов"),
    ITEM5("Производственная"),
    ITEM6("Преддипломная");

    private final String name;

    public String getName() {
        return name;
    }
}
