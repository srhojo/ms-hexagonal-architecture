package io.github.srhojo.utils.mappers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface OuterMapper<O, I> {
    /**
     * Map method from inner to outer object
     *
     * @param inner object to map
     * @return outer object
     */
    O mapToOuter(I inner);


    default O toOuter(I inner) {
        return Optional.ofNullable(inner).map(this::mapToOuter).orElse(null);
    }

    /**
     * @param inner List of inner
     * @return List of an outer objects
     */
    default List<O> toOuter(final List<I> inner) {
        return inner != null ? inner.stream().map(this::mapToOuter).collect(Collectors.toList())
                : Collections.emptyList();
    }
}