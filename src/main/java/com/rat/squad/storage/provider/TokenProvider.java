package com.rat.squad.storage.provider;

/**
 * Сам декоратор который является дополнительным слоем абстракции
 * У него есть метод validateToken() который отвечает за валидацию
 * В зависимости от того, какая реализация нужна можно ее гибко менять, во всех или в конкретных местах проекта
 */
public interface TokenProvider {
    boolean validateToken(String token);
}
