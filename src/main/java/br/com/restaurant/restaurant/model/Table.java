package br.com.restaurant.restaurant.model;

public enum Table {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    private Number number;

    Table(Number number) {
        this.number = number;
    }
}
