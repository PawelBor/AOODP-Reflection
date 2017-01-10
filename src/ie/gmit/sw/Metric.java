package ie.gmit.sw;

public class Metric {

    private int inDegree;
    private int outDegree;
    private String clsName;

    //Returns integer: the In-Degree of the class...
    public int getInDegree() {
            return inDegree;
        }
        //Sets integer: the In-Degree of the class...
    public void setInDegree(int inDegree) {
            this.inDegree = inDegree;
        }
        //Returns integer: the OUT-Degree of the class...
    public int getOutDegree() {
            return outDegree;
        }
        //Sets integer: the OUT-Degree of the class...
    public void setOutDegree(int outDegree) {
            this.outDegree = outDegree;
        }
        //Returns String: Name of the class...
    public String getClsName() {
            return clsName;
        }
        //Sets String: Name of the class...
    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    //method for calculating stability of the class, using IN/OUT Degrees
    public float getStability() {
            float stability;
            // formulae for stability of the class provided In/Out Degrees of said class
            stability = ((float) getOutDegree() / ((float) getInDegree() + (float) getOutDegree()));
            return stability;

        } //END getStability
} //END METRIC