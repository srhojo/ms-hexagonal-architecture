package io.github.srhojo.utils.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Interface who extend from InnerMapper and OuterMapper interfaces
 * </p>
 *
 * @param <I> Inner Object
 * @param <O> Outer Object
 */
public interface Mapper<I, O> extends InnerMapper<I, O>, OuterMapper<O, I> {
    /**
     * Map method from inner to outer object
     *
     * @param inner object to map
     * @return outer object
     */
    O mapToOuter(I inner);

    /**
     * @param inner List of inner
     * @return List of an outer objects
     */
    default List<O> toOuter(final List<I> inner) {
        return inner != null ? inner.stream().map(this::mapToOuter).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
