package com.nure.backGardens.BusinnesLogick;


import com.nure.backGardens.entites.IotEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GetBestWay {
    public static ArrayList<IotEntity> getBestWay(ArrayList<IotEntity> iouArr) throws IOException {
        return getBest(iouArr);
    }

    public static ArrayList<ArrayList<IotEntity>> getTmp(ArrayList<IotEntity> args) throws IOException {
        LinkedList<IotEntity> list = new LinkedList<>();
        for (IotEntity arg : args) {
            list.add(arg);
        }
        ArrayList<ArrayList<IotEntity>> result = new ArrayList<>();
        result.add(new ArrayList<IotEntity>());

        for (int i = 0; i < list.size(); i++) {
            ArrayList<ArrayList<IotEntity>> current = new ArrayList<>();

            Iterator<ArrayList<IotEntity>> resultIter = result.iterator();
            while (resultIter.hasNext()) {
                ArrayList<IotEntity> l = resultIter.next();
                for (int j = 0; j < l.size() + 1; j++) {
                    l.add(j, list.get(i));
                    ArrayList<IotEntity> temp = new ArrayList<>(l);
                    current.add(temp);
                    l.remove(j);
                }
            }
            result = new ArrayList<>(current);
        }
        return result;
    }

    public static ArrayList<IotEntity> getBest(ArrayList<IotEntity> tmp) throws IOException {
        ArrayList<ArrayList<IotEntity>> all = getTmp(tmp);
        double best = Double.MAX_VALUE;
        double tmpSize;
        ArrayList<IotEntity> res = null;
        for (ArrayList<IotEntity> iotEntities : all) {
            tmpSize = 0;
            for (int i = 0; i < iotEntities.size() - 1; i++) {
                tmpSize += getLentgh(iotEntities.get(i), iotEntities.get(i + 1));

            }
            if (tmpSize < best) {
                best = tmpSize;
                res = iotEntities;
            }
        }
        return res;
    }

    public static double getLentgh(IotEntity a, IotEntity b) {
        return Math.sqrt(Math.pow(a.getxCoordinate() - b.getxCoordinate(), 2) + Math.pow(a.getyCoordinate() - b.getyCoordinate(), 2));
    }


}
