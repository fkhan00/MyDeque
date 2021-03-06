import java.util.*;
@SuppressWarnings("unchecked")
public class MyDeque<E>{
  public E[] data;
  public int size, start, end;
  public MyDeque(){
    size = 0;
    start = 0;
    end = 0;
    data = (E[]) new Object[10000];}

  public MyDeque(int startingCapacity){
    size = startingCapacity;
    end = startingCapacity - 1;
    start = 0;
    data = (E[])new Object[startingCapacity];}

  public int size(){
    return size;}

  public String toString(){
    if(size() == 0){
      return "{}";}
    String output = "{";
    if(end < start){
    for(int i = start; i < data.length; i++){
      if(data[i] != null){
      output +=  data[i] + " ";}}
    for(int i = 0; i <= end; i++){
      if(data[i] != null){
      output += data[i] + " ";}}}
    else{
      for(int i = start; i <= end; i++){
        if(data[i] != null){
        output += data[i] +" ";}}
    }
    return output.substring(0, output.length() - 1) + "}";}

  public E getFirst(){
    if(size() == 0){
      throw new NoSuchElementException("NoSuchElementException");}
    return data[start];}

  public E getLast(){
    if(size() == 0){
      throw new NoSuchElementException("NoSuchElementException");}
    return data[end];}

  public E removeFirst(){
    if(size() == 0){
      throw new NoSuchElementException("NoSuchElementException");}
    E temp = getFirst();
    start = (start + 1) % (data.length);
    size --;
    return temp;}


  public E removeLast(){
    if(size() == 0){
      throw new NoSuchElementException("[]");}
    E temp = getLast();
    if(end == 0){
      end = data.length - 1;}
    else{
      end --;}
    size --;
    return temp;}

  public void addFirst(E element){
    if(element == null){
      throw new NullPointerException("please enter a non-null element");}
    if(end - start == data.length - 1 || end - start == 1){
      resize();
      start = data.length - 1;
      size ++;
      data[start] = element;}
    else if(start == 0){
      start = data.length - 1;
      data[start] = element;
      size ++;
    }
    else{
      start --;
      data[start] = element;
      size ++;}}

  public void addLast(E element){
    if(element == null){
      throw new NullPointerException("NullPointerException");}
    if(start == data.length - 1 || end - start == 1){
      resize();
      end++;
      size ++;
      data[end] = element;}
    else{
      end = (end + 1) % (data.length);
      size ++;
      data[end] = element;}}

  public void resize(){
    E[] temp = (E[]) new Object[data.length * 2];
    if(end < start){
      for(int i = start; i < data.length; i++){
        temp[i] = data[i];}
      for(int j = 0; j <= end; j++){
        temp[j] = data[j];}}
    else{
      for(int i = start; i <= end; i++){
        temp[i] = data[i];}}
    data = temp;}

  public static double eval(String s){
    Scanner expression = new Scanner(s);
    MyDeque<Double> pending = new MyDeque<Double>();
    Double op = 0.0;
    while (expression.hasNext()) {
      String message = expression.next();
      if(message.equals("+")) pending.addFirst(pending.removeFirst() + pending.removeFirst());
      else if (message.equals("*")) pending.addFirst(pending.removeFirst() * pending.removeFirst());
      else if(message.equals("/")) pending.addFirst(Math.pow(pending.removeFirst(), -1) * pending.removeFirst());
      else if (message.equals("-")) pending.addFirst(pending.removeFirst() * -1 + pending.removeFirst());
      else if (message.equals("%")){
      op = pending.removeFirst();
      pending.addFirst(pending.removeFirst() % op);}
      else pending.addFirst(Double.parseDouble(message));}
  return pending.removeFirst();}
}
