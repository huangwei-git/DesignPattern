/*
    包括：
        1. 抽象工厂
        2. 具体工厂
        3. 抽象产品
        4. 具体产品
 */


// 抽象产品：操作系统
interface OperatingSystem{
    void run();
}

// 具体产品：Windows
class WindowsSystem implements OperatingSystem {
    @Override
    public void run() {
        System.out.println("running windows");
    }
}

// 具体产品：Linux
class LinuxSystem implements OperatingSystem{
    @Override
    public void run() {
        System.out.println("running linux");
    }
}

// 抽象产品：App
interface Application{
    void open();
}

// 具体产品：Excel
class Excel implements Application{
    @Override
    public void open() {
        System.out.println("open excel");
    }
}

// 具体产品：Word
class Word implements Application{
    @Override
    public void open() {
        System.out.println("open word");
    }
}

// 抽象工厂
interface SoftWareFactory{
    OperatingSystem createOS();
    Application createApp();
}


// 具体Windows工厂
class WindowsFactory implements SoftWareFactory{
    @Override
    public OperatingSystem createOS() {
        return new WindowsSystem();
    }

    @Override
    public Application createApp() {
        return new Excel();
    }
}

// 具体Linux工厂
class LinuxFactory implements SoftWareFactory{
    @Override
    public OperatingSystem createOS() {
        return new LinuxSystem();
    }

    @Override
    public Application createApp() {
        return new Word();
    }
}


class Main{
    public static void main(String[] args) {
        SoftWareFactory windowsFactory = new WindowsFactory();
        OperatingSystem windowsOS = windowsFactory.createOS();
        Application windowsApp = windowsFactory.createApp();

        windowsOS.run();
        windowsApp.open();

        System.out.println("---------------");

        SoftWareFactory linuxFactory = new LinuxFactory();
        OperatingSystem linuxOS = linuxFactory.createOS();
        Application linuxApp = linuxFactory.createApp();

        linuxOS.run();
        linuxApp.open();
    }
}