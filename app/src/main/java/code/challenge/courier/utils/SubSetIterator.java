package code.challenge.courier.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubSetIterator<T> implements Iterator<List<T>> {

  private final SubSetGenerator<T> generator;
  private final int length;

  private final List<T> currentSubSet;

  private final int[] memoryBit;


  public SubSetIterator(final SubSetGenerator<T> generator) {
    this.generator = generator;
    this.length = generator.originalVector.size();
    this.currentSubSet = new ArrayList<>();
    this.memoryBit = new int[length + 2];
  }

  @Override
  public boolean hasNext() {
    return memoryBit[length + 1] != 1;
  }

  @Override
  public List<T> next() {
    currentSubSet.clear();
    for (int index = 1; index <= length; index++) {
      if (memoryBit[index] == 1) {
        T value = this.generator.originalVector.get(index - 1);
        currentSubSet.add(value);
      }
    }
    int i = 1;
    while (memoryBit[i] == 1) {
      memoryBit[i] = 0;
      i++;
    }
    memoryBit[i] = 1;

    return new ArrayList<>(currentSubSet);
  }


  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

}
