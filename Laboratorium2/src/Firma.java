import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Firma implements Iterable<Pracownik> {
    private final ArrayList<Pracownik> workers;

    public Firma(){
        workers = new ArrayList<>();
    }

    public void addWorker(Pracownik worker)
    {
        workers.add(worker);
    }

    public int getSize()
    {
        return workers.size();
    }

    public void printWorker()
    {
        for(Pracownik p : workers)
            System.out.println(p);
    }

    public Iterator<Pracownik> iterator()
    {
        return new Iterator<Pracownik>() {
            Iterator<Pracownik> iterator = workers.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Pracownik next() {
                return iterator.next();
            }
        };
    }

    public Iterator<Pracownik> iterator(Stanowisko nazwa)
    {
        return new Iterator<Pracownik>() {
            Iterator<Pracownik> iterator = workers.stream().filter(w -> w.getStanowisko() == nazwa).iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Pracownik next() {
                return iterator.next();
            }
        };
    }

    public Double averageOfSalary()
    {
        var sum = new AtomicReference<>(0D);
        this.forEach(p->sum.updateAndGet(v->v + p.getSalary()));
        var result = sum.get() / this.getSize();
        return result;
    }

    public Double averageOfSalary(Stanowisko nazwa)
    {
        var sum = new AtomicReference<>(0D);
        var count = new AtomicInteger();

        this.iterator(nazwa).forEachRemaining(p-> {sum.updateAndGet(v->v+p.getSalary());
        count.incrementAndGet();
        });

        var result = sum.get() / count.intValue();
        return result;
    }
}
