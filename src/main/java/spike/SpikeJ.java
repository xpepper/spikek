package spike;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpikeJ {
    @Nullable
    public String name() {
        return null;
    }

    public static void main(String[] args) {
        SpikeK spikeK = new SpikeK();
        String name = new SpikeJ().name();
        System.out.println(name.length());
    }
}

