package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    protected HashMap<Long,TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();
    protected Long idSequence = 1L;

    public TimeEntry create(TimeEntry timeEntry){
        timeEntry.setId(idSequence++);
        timeEntries.put(timeEntry.getId(),timeEntry);
        return timeEntries.get(timeEntry.getId());
    }

    public TimeEntry find(Long id){
        TimeEntry e = timeEntries.get(id);
        return timeEntries.get(id);
    }
    public List<TimeEntry> list(){
        List<TimeEntry> entries = new ArrayList<TimeEntry>();
        entries.addAll(timeEntries.values());
        return entries;

    }

    public TimeEntry update(Long id, TimeEntry timeEntry){
        if(timeEntries.get(id) == null){
            return null;
        }
        timeEntry.setId(id);
        timeEntries.put(id,timeEntry);
        return timeEntries.get(id);
    }

    public void delete(Long id){
        timeEntries.remove(id);
    }

}
