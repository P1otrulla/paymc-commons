package dev.piotrulla.paymc.commons.intrange;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntRangeSerializer implements ObjectSerializer<IntRange> {

    private static final String SEPARATOR = ":";
    private static final String SOLO_RANGES_SEPARATOR = ",";

    @Override
    public boolean supports(@NotNull Class<? super IntRange> type) {
        return IntRange.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(IntRange object, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        String contextRange = object.min() + SEPARATOR + object.max();
        data.add("context", contextRange);

        List<Integer> intRages = object.soloRanges();

        if (intRages.isEmpty() || intRages == null) {
            return;
        }

        String soloRanges = intRages.stream()
                .map(intRage -> intRage + "")
                .collect(Collectors.joining(SOLO_RANGES_SEPARATOR));

        data.add("soloRanges", soloRanges);
    }

    @Override
    public IntRange deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        String contextRange = data.get("context", String.class);

        if (contextRange == null) {
            throw new RuntimeException("Missing context range");
        }

        String[] parts = contextRange.split(SEPARATOR);

        if (parts.length != 2) {
            throw new RuntimeException("Invalid context range format, expected 'min:max'");
        }

        try {
            Integer.parseInt(parts[0]);
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid context range format, expected 'min:max'");
        }

        int min = Integer.parseInt(parts[0]);
        int max = Integer.parseInt(parts[1]);

        String soloRanges = data.get("soloRanges", String.class);

        if (soloRanges == null) {
            return new IntRange(min, max);
        }

        String[] soloRangesParts = soloRanges.split(SOLO_RANGES_SEPARATOR);

        List<Integer> soloRangesList = new ArrayList<>();

        for (String soloRange : soloRangesParts) {
            soloRangesList.add(Integer.parseInt(soloRange));
        }

        return new IntRange(min, max, soloRangesList);
    }
}
