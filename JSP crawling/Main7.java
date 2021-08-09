package com.koreait.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/*
    2021년 8월 5일 과제
    - 바나프레소 지점의 이름과 주소를 크롤링 해오기
 */
public class Main7 {
    public static void main(String[] args) {
        String DRIVER_ID = "webdriver.chrome.driver";
        String DRIVER_PATH = "/Users/soyounjeong/Desktop/develop/yoon/JSP/chromedriver";

        System.setProperty(DRIVER_ID,DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        String base_url = "https://www.banapresso.com/store";

        try{
            driver.get(base_url); // 주소를 얻어와 읽기
            Thread.sleep(1000); // 1초마다 멈췄다 실행

            List<WebElement> store = driver.findElements(By.cssSelector("i")); // 가게 이름
            List<WebElement> address = driver.findElements(By.cssSelector(".store_name_map > span")); // 주소
            for(int i = 1; i< store.size(); i++){
                System.out.println("가게 이름 : " + store.get(i).getText());
                System.out.println("주소 : " + address.get(i).getText());
                System.out.println("----------------------------------------------------------------");
                Thread.sleep(1000); // 1초마다 멈췄다 실행
            }


            // 페이지 넘기기
            int j = 2;
            while(true){
                try{
                    WebElement Nextpage = driver.findElement(By.cssSelector(".pagination > ul> li:nth-child(" + j+ ")"));
                    Nextpage.click();
                    Thread.sleep(1000);

                    List<WebElement>  store1 = driver.findElements(By.cssSelector("i")); // 가게 이름
                    List<WebElement> address1 = driver.findElements(By.cssSelector(".store_name_map > span")); // 주소

                    for(int i = 1; i< store1.size(); i++){
                        System.out.println("가게 이름 : " + store1.get(i).getText());
                        System.out.println("주소 : " + address1.get(i).getText());
                        System.out.println("----------------------------------------------------------------");
                        Thread.sleep(1000); // 1초마다 멈췄다 실행
                    }


                    if(j < 5){ // 페이지 번호가 5되기 전까지 돌리기
                        j++;
                    }else if(j == 5){ // 페이지 번호가 5면
                        WebElement Next = driver.findElement(By.cssSelector(".pagination > span > a")); // 다음페이지 이동 태그
                        Next.click(); // 다음페이지 클릭하면
                        j = 1; // 다시 읽기
                    }
                }catch (Exception e){ // 해당되는게 없을 시
                    System.out.println("프로그램을 종료합니다.");
                    break; // break문을 통해 프로그램 빠져 나오기
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
