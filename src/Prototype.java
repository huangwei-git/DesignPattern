import java.io.*;

/**
 * 【原型模式】：用于需要对象副本的情况，提供了一种灵活且高效的方法来处理对象的复制需求。
 *     例如：比赛获得的奖牌，除了名字和奖项等级外，其他信息都一致，因此可以创建一个填充好信息的奖牌作为原型，
 *         然后克隆该原型对象，修改名字、等级
 *
 * 问题：
 *     某些情况需要创建对象副本，但是复制一个对象的成本较大，或是希望避免与对象的具体类耦合。
 *         例如：对象创建过程复杂，或是对象包含大量共享状态时，常规方法可能导致性能下降。
 *
 * 解决：
 *     原型模式（Prototype）通过复制现有对象来创建新对象，而不是从头构建。
 *     这允许我们更高效地创建新对象，同时避免与新对象类的直接耦合。
 *     核心概念是，在原型对象的基础性进行克隆，使得新对象有与原型相同的初始状态。
 *
 * 角色：
 *     1. 抽象原型（Abstract Prototype）：声明 clone 方法，作为所有具体Prototype的基类或接口。
 *     2. 具体原型（Concrete Prototype）：实现/重写 clone 方法，从自身创建一个副本，是可复制的对象。
 *     3. 访问类（Visitor）：使用Concrete Prototype中的clone方法复制新对象。
 *
 * 作用：
 *     1. 减少对象的创建成本：避免了复杂对象的重复初始化过程，提高创建对象的效率。
 *     2. 避免与具体类耦合：客户端通过克隆方法创建对象，而无需知道具体类的细节，降低了耦合度。
 *     3. 灵活：可以在运行时动态添加或删除原型，使用不同的对象创建需求。
 *     4. 支持动态配置：可以通过克隆来定制对象的不同配置，而无需修改代码。
 *
 * 注意：
 *     1. 深克隆问题：原型模式默认进行浅克隆，即复制对象本身和其引用。如果对象内部包含其他对象的引用，可能需要实现深克隆来复制整个对象结构。
 *     2. 克隆方法的实现：某些对象不容易进行克隆，特别是涉及到文件、网络连接等资源的情况。
 */


// Object 类中提供了 clone() 方法实现浅克隆。
// Cloneable 接口是角色中的 “Abstract Prototype”
// 创建一个实现 Cloneable 接口的 Concrete Prototype 类：重写 clone 方法
class Sheep implements Cloneable{
    private String name;

    public Sheep(String type){
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Sheep clone() {
        try{
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e){
            return null;
        }
    }
}

// Run!
public class Prototype {
    public static void main(String[] args) {
        Sheep lazySheep = new Sheep("lazySheep");

        Sheep duoLi = lazySheep.clone();
        duoLi.setName("duoLi");

        System.out.println(lazySheep.getName());
        System.out.println(duoLi.getName());
        System.out.println(lazySheep == duoLi);
    }
}



/**
 * 深克隆例子
 *      深克隆通过将对象 [序列化] 到文件中，再读取来实现
 *      因此需要实现 Serializable 接口
 * **/

// 创建一个非基本数据类型（浅克隆不会改变引用，需要深克隆）
class Human implements Serializable {
    private String name;

    public Human(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 创建一个奖杯类，作为Concrete Prototype
// 该具体原型、以及其中的Human字段都需要序列化，因此Human类也要实现Serializable接口
class Trophy implements Cloneable, Serializable{
    protected Human human;

    public Trophy(Human human) {
        this.human = human;
    }

    public String getName() {
        return this.human.getName();
    }

    public void setName(String name) {
        this.human.setName(name);
    }

    // 重写 clone 方法
    @Override
    public Trophy clone(){
        try {
            // 创建[Object输出流对象]
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./a.txt"));
            oos.writeObject(this);

            // 创建 [Object输入流对象]
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./a.txt"));
            Trophy cloneTrophy = (Trophy) ois.readObject();

            return cloneTrophy;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


// Run!
//public class Prototype {
//    public static void main(String[] args) {
//        Human h1 = new Human("ZhangSan");
//        Trophy trophy = new Trophy(h1);
//
//        Trophy trophy2 = trophy.clone();
//        trophy2.setName("LiSi");
//
//        System.out.println(trophy.getName());
//        System.out.println(trophy2.getName());
//    }
//}
