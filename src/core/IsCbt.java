package core;

public class IsCbt {

    public static class Info{
        int height;
        boolean isCbt;
        boolean isFull;

        public Info(int height,boolean isCbt,boolean isFull){
            this.height = height;
            this.isCbt = isCbt;
            this.isFull = isFull;
        }
    }












    public static Info isCbt(Node x){
        if(x == null){
            return new Info(0,true,true);
        }
        Info r = isCbt(x.right);
        Info l = isCbt(x.left);
        int height = Math.max(r.height,l.height)+1;
        boolean isFull = r.isFull && l.isFull && r.height == l.height;
        boolean isCbt = false;
        if (isFull){
            isCbt = true;
        }else {
            if(l.isCbt && r.isCbt){
                if(l.height  == r.height + 1 && r.isFull){
                    isCbt = true;
                }else if (l.height  == r.height + 1 && l.isFull && r.isFull){
                    isCbt = true;
                }else if(l.height == r.height && l.isFull){
                    isCbt = true;
                }
            }

        }

        return new Info(height,isCbt,isFull);
    }






















    public static Info process(Node x){
        if(x == null){
            return new Info(0,true,true);
        }
        Info lInfo = process(x.left);
        Info rInfo = process(x.right);
        int height = Math.max(lInfo.height,rInfo.height) + 1;
        boolean isFull = lInfo.isFull && rInfo.isFull && (lInfo.height == rInfo.height);
        boolean isCbt = false;
        if(isFull){
            isCbt = true;
        }else {
            if(lInfo.isCbt && rInfo.isCbt){
                if(lInfo.isCbt && rInfo.isFull && lInfo.height == rInfo.height + 1){
                    isCbt = true;
                }else if(lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height + 1){
                    isCbt = true;
                }else if(lInfo.isFull && rInfo.isCbt && lInfo.height == rInfo.height){
                    isCbt = true;
                }
            }
        }
        return new Info(height,isCbt,isFull);
    }
















































}
