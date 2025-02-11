package by.it.group310971.m_nesterik.lesson02.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        Arrays.sort(events, (o1,o2) ->
        {
            if(o1.stop!=o2.stop){
                return Integer.compare(o1.stop,o2.stop);
                // Если время окончания событий o1.stop и o2.stop различаются, сортируем по возрастанию времени окончания.
            }else return Integer.compare(o1.start,o2.start);
            // Если время окончания одинаковое, сортируем по возрастанию времени начала событий o1.start и o2.start.
        });
        int i = 0;

        int curentEventTime = 0;
        while (i < events.length){
            if (curentEventTime > events[i].start) ++i;
                // Если время начала текущего события events[i].start меньше или равно текущему времени curentEventTime, пропускается событие (++i;), так как оно будет пересекаться с предыдущим.
            else
            //Иначе добавляется текущее событие events[i] в список result и обновляет curentEventTime на время окончания этого события events[i].stop.
            {
                curentEventTime = events[i].stop;
                result.add(events[i]);
            }
        }





        return result;          //вернем итог
    }
}