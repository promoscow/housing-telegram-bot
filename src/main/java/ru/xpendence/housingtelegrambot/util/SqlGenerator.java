package ru.xpendence.housingtelegrambot.util;

import java.util.Objects;

/**
 * Генератор SQL-запроса для популирования таблицы flats.
 *
 * @author Вячеслав Чернышов
 * @since 06.02.2021
 */
public class SqlGenerator {

    private static final String HEADER = "insert into flats(id, active, created, housing, section, floor, flat)\n" +
            "values ";
    private static final String INSERT_TEMPLATE = "(uuid_generate_v4(), 'ENABLED', now(), %d, %d, %d, %d)";

    public static void main(String[] args) {

        var section7 = createSection7();
        var section8 = createSection8();
        var builder = new StringBuilder();
        builder.append(HEADER);
        composeQuery(section7, builder, 7);
        composeQuery(section8, builder, 8);
        System.out.println(builder.toString().trim());
    }

    private static void composeQuery(Short[][] section, StringBuilder builder, int sectionNumber) {
        for (int i = 0; i < section.length; i++) {
            for (int j = 0; j < section[i].length; j++) {
                if (Objects.nonNull(section[i][j])) {
                    builder.append(String.format(INSERT_TEMPLATE, 1, sectionNumber, i + 1, section[i][j]));
                    builder.append(",\n");
                }
            }
        }
    }

    private static Short[][] createSection7() {
        Short[][] section7matrix = new Short[16][6];
        section7matrix[0] = new Short[]{424, 425, 426, 427, null, null};
        var flat = (short) 428;
        for (int i = 1; i < 15; i++) {
            var floorMatrix = new Short[6];
            for (int j = 0; j < 6; j++) {
                floorMatrix[j] = flat++;
            }
            section7matrix[i] = floorMatrix;
        }
        section7matrix[15] = new Short[]{512, 513, 514, 515, 516, null};
        return section7matrix;
    }

    private static Short[][] createSection8() {
        Short[][] section8matrix = new Short[16][8];
        section8matrix[0] = new Short[]{517, 518, 519, 520, 521, 522, null, null};
        var flat = (short) 523;
        for (int i = 1; i < 15; i++) {
            var floorMatrix = new Short[8];
            for (int j = 0; j < 8; j++) {
                floorMatrix[j] = flat++;
            }
            section8matrix[i] = floorMatrix;
        }
        section8matrix[15] = new Short[]{635, 636, 637, 638, 639, 640, 641, 642};
        return section8matrix;
    }
}
