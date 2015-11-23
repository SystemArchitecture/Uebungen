// Automatically generated event hookup file.

package tmp.sunw.beanbox;
import beans.ImagePlotSink;
import framework.Pushable;
import framework.PipesFiltersEvent;

public class ___Hookup_1ea0f89091 implements framework.Pushable, java.io.Serializable {

    public void setTarget(beans.ImagePlotSink t) {
        target = t;
    }

    public void write(framework.PipesFiltersEvent arg0) {
        target.write(arg0);
    }

    private beans.ImagePlotSink target;
}
