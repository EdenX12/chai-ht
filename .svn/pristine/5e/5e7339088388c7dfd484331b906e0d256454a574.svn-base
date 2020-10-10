package com.tian.sakura.cdd.srv.service.product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import com.tian.sakura.cdd.srv.service.product.param.DefaultPrdCommissionCalculateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tian.sakura.cdd.db.manage.log.UserBonusLogManage;
import com.tian.sakura.cdd.db.manage.order.OrderDetailManage;
import com.tian.sakura.cdd.db.manage.product.ProductReviewStatManage;
import com.tian.sakura.cdd.srv.service.task.TaskAwardService;
import com.tian.sakura.cdd.srv.service.task.TaskLineService;
import org.springframework.util.StopWatch;


/**
 * 商品佣金相关
 * 计算买家立返
 * 计算同组最低躺赢金
 * 已拆满的任务线个数
 * 当前未满任务线的份额数
 * 参与的拆家人数
 * 关注人数
 * @author liuhg
 *
 */
@Service
public class ProductCommissionService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskAwardService taskAwardService;
	@Autowired
	private TaskLineService taskLineService;
	@Autowired
	private ProductReviewStatManage productReviewStatManage;
	@Autowired
	private OrderDetailManage orderDetailManage;
	@Autowired
	private UserBonusLogManage userBonusLogManage;

	private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public void doSynCalculate(final String productId, final String taskId) {
		CountDownLatch latch = new CountDownLatch(6);

		try {
			//已拆满的任务线个数
			Future<Integer> totalNumberFuture = calculateTotalNumber(productId, latch);
			// 紧邻已满任务线的当前未满任务线的份额数
			Future<Integer> receivedTaskFuture = calculateReceiveTask(productId, latch);
			// 参与的拆家人数
			Future<Integer> userCountFuture = calculateUserCount(productId, latch);
			// 商品销量
			Future<Integer> saleQuantityFuture = calculateSellQuantityByPrdId(productId, latch);
			// 拆家已躺赢人次
			Future<Integer> hasWinCntFuture = calculateCntByProductId(productId, latch);
			// 前未满任务线的份额数
			Future<Integer> currentReceivedTaskFuture = calcullateReceivedTaskCnt(productId, taskId, latch);

			// 最多等2秒
			latch.await(2, TimeUnit.SECONDS);

			int totalNumber = totalNumberFuture.get(2, TimeUnit.SECONDS);
			int receivedTask = receivedTaskFuture.get(2, TimeUnit.SECONDS);
			int userCount = userCountFuture.get(2, TimeUnit.SECONDS);
			int saleQuantity = saleQuantityFuture.get(2, TimeUnit.SECONDS);
			int hasWinCnt = hasWinCntFuture.get(2, TimeUnit.SECONDS);
			int currentReceivedTask = currentReceivedTaskFuture.get(2, TimeUnit.SECONDS);


		} catch (Exception e) {

		}

	}

	private Future<Integer> calculateTotalNumber(final String productId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				return taskLineService.getFullTaskLineCntByProductId(productId);
			} finally {
				latch.countDown();
			}
		});
	}

	private Future<Integer> calculateReceiveTask(final String productId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				return taskLineService.receivedTaskCntByProductId(productId);
			} finally {
				latch.countDown();
			}
		});
	}

	private Future<Integer> calculateUserCount(final String productId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				return taskLineService.userCount(productId);
			} finally {
				latch.countDown();
			}
		});
	}

	private Future<Integer> calculateSellQuantityByPrdId(final String productId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				Map map = orderDetailManage.getSellQuantityByPrdId(productId);
				Integer saleQuantity = 0;
				if(map != null) {
					BigDecimal tmp = (BigDecimal) map.get("cnt");
					if(tmp != null) {
						saleQuantity = tmp.intValue();
					}
				}
				return saleQuantity;
			} finally {
				latch.countDown();
			}
		});
	}

	public Future<Integer> calculateCntByProductId(final String productId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				return userBonusLogManage.selectCntByProductId(productId);
			} finally {
				latch.countDown();
			}
		});
	}

	public Future<Integer> calcullateReceivedTaskCnt(final String productId,final String taskId, CountDownLatch latch) {
		return executorService.submit(() -> {
			try {
				int currentReceivedTask = 0;
				if (taskId != null && !taskId.contentEquals("")) {
					currentReceivedTask = taskLineService.receivedTaskCntByTaskId(taskId);
				}
				return currentReceivedTask;
			} finally {
				latch.countDown();
			}
		});
	}

	public PrdCommissionCalculateResult getProductCommission(PrdCommissionParameter comminssionParameter) {
		StopWatch stopWatch = new StopWatch("商品任务数据计算时间");
		stopWatch.start("商品任务指标计算");
		String productId = comminssionParameter.getProductId();
		try {
			return asynCalculate(stopWatch, comminssionParameter);
		} finally {
			if (stopWatch.isRunning()) {
				stopWatch.stop();
			}
			logger.info(stopWatch.prettyPrint());
		}
	}

	private PrdCommissionCalculateResult asynCalculate(StopWatch stopWatch, PrdCommissionParameter comminssionParameter) {
		CountDownLatch latch = new CountDownLatch(6);
		String productId = comminssionParameter.getProductId();
		BigDecimal productTotalCommission = comminssionParameter.getTotalReward();// 获取商品总佣金
		logger.info("商品[{}]d的总佣金[{}]", productId, productTotalCommission);
		BigDecimal buyerCommission = BigDecimal.ZERO;//买家立返佣金
		BigDecimal sameGroupCommission = BigDecimal.ZERO;//同组躺赢佣金
		if (productTotalCommission.compareTo(BigDecimal.ZERO) != 0) {
			// 计算买家立返
			buyerCommission = taskAwardService.buyerCommission(productTotalCommission);

			// 计算同组最低躺赢金
			int taskNumber = comminssionParameter.getTaskNumber();// 该商品每条任务线上的最大任务数
			logger.info("产品[{}]设置的每条任务线的任务数{}", productId, taskNumber);

			sameGroupCommission = BigDecimal.ZERO;
			if (taskNumber > 0) {
				sameGroupCommission = taskAwardService.sameGroupCommission(productTotalCommission, taskNumber);
			}
			logger.info("产品[{}]任务最低躺赢金[{}]", productId, sameGroupCommission);
		}
		//已拆满的任务线个数
		Future<Integer> totalNumberFuture = calculateTotalNumber(productId, latch);
		//当前未满任务线的份额数
		Future<Integer> receivedTaskFuture = calculateReceiveTask(productId, latch);
		//参与的拆家人数
		Future<Integer> userCountFuture = calculateUserCount(productId, latch);
		//关注人数
		int totalFocus = productReviewStatManage.totalFocus(productId);

		//商品销量
		Future<Integer> saleQuantityFuture = calculateSellQuantityByPrdId(productId, latch);
		// 拆家已躺赢人次
		Future<Integer> hasWinCntFuture = calculateCntByProductId(productId, latch);


		//当前未满任务线的份额数
		String taskId = comminssionParameter.getTaskId();
		// 前未满任务线的份额数
		Future<Integer> currentReceivedTaskFuture = calcullateReceivedTaskCnt(productId, taskId, latch);
		int totalNumber = 0;
		int receivedTask = 0;
		int userCount = 0;
		int saleQuantity = 0;
		int hasWinCnt = 0;
		int currentReceivedTask = 0;

		try {
			// 最多等2秒
			latch.await(2, TimeUnit.SECONDS);

			totalNumber = totalNumberFuture.get(2, TimeUnit.SECONDS);
			receivedTask = receivedTaskFuture.get(2, TimeUnit.SECONDS);
			userCount = userCountFuture.get(2, TimeUnit.SECONDS);
			saleQuantity = saleQuantityFuture.get(2, TimeUnit.SECONDS);
			hasWinCnt = hasWinCntFuture.get(2, TimeUnit.SECONDS);
			currentReceivedTask = currentReceivedTaskFuture.get(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		} finally {
		}


		PrdCommissionCalculateResult calculateResult = DefaultPrdCommissionCalculateResult.builder()
				.productId(productId)
				.successReward(buyerCommission)
				.everyReward(sameGroupCommission)
				.totalNumber(totalNumber)
				.receivedTask(receivedTask)
				.userCount(userCount)
				.totalFocus(totalFocus)
				.currentReceivedTask(currentReceivedTask)
				.sellQuantity(saleQuantity)
				.hasWinCnt(hasWinCnt)
				.build();
		return calculateResult;
	}

	private PrdCommissionCalculateResult synCalculate(StopWatch stopWatch, PrdCommissionParameter comminssionParameter) {
		String productId = comminssionParameter.getProductId();
		BigDecimal productTotalCommission = comminssionParameter.getTotalReward();// 获取商品总佣金
		logger.info("商品[{}]d的总佣金[{}]", productId, productTotalCommission);
		BigDecimal buyerCommission = BigDecimal.ZERO;//买家立返佣金
		BigDecimal sameGroupCommission = BigDecimal.ZERO;//同组躺赢佣金
		if (productTotalCommission.compareTo(BigDecimal.ZERO) != 0) {
			// 计算买家立返
			stopWatch.start("买家立返金额");
			buyerCommission = taskAwardService.buyerCommission(productTotalCommission);
			stopWatch.stop();

			stopWatch.start("任务最低躺赢金额");
			// 计算同组最低躺赢金
			int taskNumber = comminssionParameter.getTaskNumber();// 该商品每条任务线上的最大任务数
			logger.info("产品[{}]设置的每条任务线的任务数{}", productId, taskNumber);

			sameGroupCommission = BigDecimal.ZERO;
			if (taskNumber > 0) {
				sameGroupCommission = taskAwardService.sameGroupCommission(productTotalCommission, taskNumber);
			}
			logger.info("产品[{}]任务最低躺赢金[{}]", productId, sameGroupCommission);
			stopWatch.stop();
		}
		//已拆满的任务线个数
		stopWatch.start("已拆满的任务线个数");
		// String productId = comminssionParameter.getProductId();
		int totalNumber = taskLineService.getFullTaskLineCntByProductId(productId);
		stopWatch.stop();
		//当前未满任务线的份额数
		stopWatch.start("紧邻已满任务线的当前未满任务线的份额数");
		int receivedTask = taskLineService.receivedTaskCntByProductId(productId);
		stopWatch.stop();
		//参与的拆家人数
		stopWatch.start("参与的拆家人数");
		int userCount = taskLineService.userCount(productId);
		stopWatch.stop();
		//关注人数
		stopWatch.start("关注人数");
		int totalFocus = productReviewStatManage.totalFocus(productId);
		stopWatch.stop();

		//商品销量
		Map map = orderDetailManage.getSellQuantityByPrdId(productId);
		Integer saleQuantity = 0;
		if(map != null) {
			BigDecimal tmp = (BigDecimal) map.get("cnt");
			if(tmp != null) {
				saleQuantity = tmp.intValue();
			}
		}
		//拆家已躺赢人次
		Integer hasWinCnt = userBonusLogManage.selectCntByProductId(productId);


		//当前未满任务线的份额数
		String taskId = comminssionParameter.getTaskId();
		int currentReceivedTask = 0;
		if (taskId != null && !taskId.contentEquals("")) {
			stopWatch.start("当前未满任务线的份额数");
			currentReceivedTask = taskLineService.receivedTaskCntByTaskId(taskId);
			stopWatch.stop();
		}

		PrdCommissionCalculateResult calculateResult = DefaultPrdCommissionCalculateResult.builder()
				.productId(productId)
				.successReward(buyerCommission)
				.everyReward(sameGroupCommission)
				.totalNumber(totalNumber)
				.receivedTask(receivedTask)
				.userCount(userCount)
				.totalFocus(totalFocus)
				.currentReceivedTask(currentReceivedTask)
				.sellQuantity(saleQuantity)
				.hasWinCnt(hasWinCnt)
				.build();
		return calculateResult;
	}
	
}
