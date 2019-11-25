package my.rinat.springbootexamples.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@RestController
@RequestMapping("/async-controller-example")
public class AsyncControllerExample {

    @GetMapping("/sync")
    public String getValueSync() {
        log.info("Request received");
        return processRequest();
    }

    @GetMapping("/asyncDeferred")
    public DeferredResult<String> getValueAsyncUsingDeferredResult() {
        log.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        ForkJoinPool.commonPool()
                .submit(() -> deferredResult.setResult(processRequest()));
        log.info("Servlet thread released");
        return deferredResult;
    }

    @GetMapping("/asyncCompletable")
    public CompletableFuture<String> getValueAsyncUsingCompletableFuture() {
        log.info("Request received");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(this::processRequest);
        log.info("Servlet thread released");
        return completableFuture;
    }

    @GetMapping("/nonCompleted")
    public CompletableFuture<String> getNonCompletedFuture() {
        log.info("Request received");
        final CompletableFuture<String> future = new CompletableFuture<>();
        // warning: ExecutorService should be shutdown
        Executors.newCachedThreadPool().submit(() -> {
            Thread.currentThread().setName("my-thread");
            future.complete(processRequest());
            log.info("Non-completed future has completed");
        });
        return future;
    }

    private String processRequest() {
        log.info("Start processing request");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        log.info("Completed processing request");
        return UUID.randomUUID().toString();
    }
}
