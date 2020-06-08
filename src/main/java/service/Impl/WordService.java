package service.Impl;

import com.google.gson.Gson;
import http.servlet.base.HttpServlet;
import http.servlet.base.HttpServletResponse;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Segmentation;
import org.apdplat.word.segmentation.Word;
import service.JerryService;

import java.util.LinkedList;
import java.util.List;

public class WordService implements JerryService {
    private String uri;
    private HttpServletResponse response;

    public WordService(String uri, HttpServletResponse response) {
        this.uri = uri;
        this.response = response;
    }

    public void service() {
        //分词服务中以第一个 & 号之后的字符串作为分词对象
        //取得参数
        if (uri.contains("&")) {
            String line = uri.substring(uri.lastIndexOf("&") + 1);
            List<Word> lw = WordSegmenter.segWithStopWords(line);
            List<String> result = new LinkedList<String>();
            for (Word w : lw) {
                result.add(w.getText());
            }
            response.textWriter(new Gson().toJson(result));
        } else {
            return;
        }

    }
}
