import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // TODO
        Building[] closed = null;
        ArrayList<Point> points = new ArrayList<>();
        //DIVIDE

        if(B.length > 1) {
            int mid = B.length/2;
            Building[] L = Arrays.copyOfRange(B,0,mid);
            Building[] R = Arrays.copyOfRange(B,mid,B.length);
            skyline(L);
            skyline(R);
            closed = closeBuildings(L,R);
            if(closed != null) {
                System.out.println("INPUT: ");
                printBuildings(L);
                printBuildings(R);

                System.out.println(" ");
                System.out.println("OUTPUT: ");
                printBuildings(closed);
                System.out.println(" ");

                points.add(new Point(closed[0].l,closed[0].h));

                for(int i = 1; i < closed.length; i ++) {
                    Building a = closed[i-1];
                    Building b = closed[i];
                        Point t1 = new Point(a.r,a.h);
                        Point t2 = new Point(b.l,b.h);

                        if(t1.x != t2.x) {
                            points.add(new Point(t1.x,0));
                            points.add(new Point(t2.x,t2.y));
                        } else {
                            points.add(t2);
                        }


                }

                points.add(new Point(closed[closed.length-1].r,0));
            }
        }



        printPoints(points);
        return points;
    }

    // Given a subdivision of a total number of buildings, solves building intersections
    public static Building[] closeBuildings(Building[] L, Building[] R) {

        //COMBINE
        Building[] combined = new Building[L.length+R.length];
        System.arraycopy(L,0,combined,0,L.length);
        System.arraycopy(R,0,combined,L.length,R.length);
        List<Building> B = new ArrayList<>(Arrays.asList(combined));


        //CONQUER
        int end1;
        int end2;
        int start1;
        int start2;

        for ( int i = 0; i < B.size()-1; i++) {

            if(B.get(i+1) != null) {

                // Identify relevant boundaries for detecting building intersections
                end1 = B.get(i).r;
                end2 = B.get(i+1).r;
                start1 = B.get(i).l;
                start2 = B.get(i+1).l;

                // If start 1 and start 2 are the same
                // and end 2 is greater than end 1



                // If there exists boundary overlap
                if( end1 >= start2 && start1 <= start2) {

                    if( end2 > end1 && start1 < start2) {

                        // Building 2 is partially inside Building 1. Make Building 2 start where Building 1 ends.
                        if(B.get(i+1).h == B.get(i).h) {
                            B.get(i+1).l = start1;
                            B.remove(i);
                            i--;

                        } else if(B.get(i).l != B.get(i+1).l){ // SHOULD BE TWO CONDITIONS HERE
                            B.get(i+1).l = end1;
                            if(B.get(i+1).r == end1) {
                                B.remove(i+1);
                                i--;
                            }
                        }

                    } else {

                        // if B2 inside B1; ends before B1, starts after B1
                        if( end2 <= end1 && start1 <= start2) {

                            // if B2 is contained
                            if(B.get(i+1).h <= B.get(i).h) {
                                B.remove(i+1);
                                i-=1;
                            } else {

                                Building tmp1 = B.remove(i);
                                Building tmp2 = B.remove(i);

                                B.add(i,new Building(tmp1.l,tmp2.l,tmp1.h));
                                B.add(i+1,tmp2);
                                B.add(i+2,new Building(tmp2.r,tmp1.r,tmp1.h));
                            }
                        } else { // B2 is uncontained,
                            if(B.get(i+1).h >= B.get(i).h) {
                                B.remove(i);
                                i--;
                            } else {
                                B.get(i+1).l = end1;
                            }
                        }
                    }
                } else if(end1 >= start2 && start1 > start2 && B.get(i+1).h <= B.get(i).h ) {
                    B.remove(i+1);
                    i--;
                } else if(end1 >= start2 && start1 > start2 ){
                    Collections.swap(B,i,i+1);
                    i--;
                }
            }
        }


        for(int i = 0; i < B.size()-1; i++) {
            if(B.get(i).l-B.get(i).r == 0) {
                B.remove(i);
                i--;
            }
            if(B.get(i).h == B.get(i+1).h) {
                if(B.get(i+1).l <= B.get(i).r && B.get(i+1).r >= B.get(i).r) {
                    B.get(i).r = B.get(i+1).r;
                    B.remove(i+1);
                    i--;
                } else if (B.get(i+1).l <= B.get(i).r && B.get(i+1).r < B.get(i).r) {
                    B.remove(i+1);
                    i--;
                } else if (B.get(i).r == B.get(i+1).l && B.get(i+1).r > B.get(i).r){
                    System.out.println("Funck");
                    B.get(i).r = B.get(i+1).r;
                    B.remove(i+1);
                    i--;
                }
            } else {
                if(B.get(i+1).l == B.get(i).l) {
                    B.remove(i);
                    i--;
                }
            }

        }

        for (int i = 0; i < B.size()-1; i ++) {
            if(B.get(i).h > B.get(i+1).h) {
                if (B.get(i + 1).l == B.get(i).l && B.get(i + 1).r > B.get(i).r) {
                    B.get(i).r = B.get(i + 1).r;
                    B.remove(i + 1);
                    i--;
                } else if ( B.get(i+1).l < B.get(i).r && B.get(i+1).r <= B.get(i).r) {
                    B.remove(i+1);
                    i--;
                }
            } else if (B.get(i).h == B.get(i+1).h && B.get(i).r == B.get(i+1).l) {
                if (B.get(i).r < B.get(i+1).r) {
                    B.get(i).r = B.get(i+1).r;
                    B.remove(i+1);
                    i--;
                }
            }
        }


        // Return all non-null Buildings in a list
        B.removeAll(Collections.singleton(null));

        return B.toArray(new Building[B.size()]);
    }



    // It would be helpful if debugging methods like this were written for us in the future
    public static void printBuildings(Building[] B) {
        for(int i = 0; i < B.length; i ++) {
            System.out.println("[" + B[i].l + ", " + B[i].h + ", " + B[i].r + "]");
        }
    }

    public static void printPoints(ArrayList<Point> a) {
        for(Point p : a) {
            System.out.println("(" + p.x + "," + p.y + ")");
        }
    }
}
