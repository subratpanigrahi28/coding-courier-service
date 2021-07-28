package code.challenge.courier.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class SubSetGenerator<T> implements Iterable<List<T>> {

  final List<T> originalVector;

  public SubSetGenerator(List<T> originalVector) {
    this.originalVector = originalVector;
  }

  @Override
  public Iterator<List<T>> iterator() {
    return new SubSetIterator<>(this);
  }

  public Stream<List<T>> stream() {
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
  }
}
