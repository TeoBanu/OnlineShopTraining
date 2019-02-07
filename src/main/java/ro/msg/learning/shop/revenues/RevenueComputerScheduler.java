package ro.msg.learning.shop.revenues;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.datamodels.Revenue;
import ro.msg.learning.shop.repos.OrderRepo;
import ro.msg.learning.shop.repos.RevenueRepo;

import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class RevenueComputerScheduler {

    private final OrderRepo orderRepo;
    private final RevenueRepo revenueRepo;

    @Autowired
    public RevenueComputerScheduler(OrderRepo orderRepo,
                                    RevenueRepo revenueRepo) {
        this.orderRepo = orderRepo;
        this.revenueRepo = revenueRepo;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void computeAndSaveRevenuesForCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        log.info("started revenue computation for " + month + "/" + day + "/" + year);
        List<Revenue> revenues = orderRepo.findRevenuesForDay(year, month, day);
        revenueRepo.saveAll(revenues);
        log.info("ended revenue computation");
    }
}
