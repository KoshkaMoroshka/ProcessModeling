package ru.sfedu.ProcessModeling.api.events;

import java.util.ArrayList;

public class EventManager {
    public ArrayList<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    public EventListener[] getListeners(){
        return listeners.toArray(new EventListener[listeners.size()]);
    }

    public void removeListener(EventListener listener){
        listeners.remove(listener);
    }

    public void updateListeners(){
        for(EventListener listener: listeners){
            listener.event();
        }
    }
}
