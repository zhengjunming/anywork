package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Suggestion;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.SuggestionService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author logan
 * @date 2017/8/18
 */
@RestController
@RequestMapping("/suggest")
@CrossOrigin
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RequestResult addSuggestion(@RequestParam("description") String description, HttpServletRequest request,
                                       @RequestParam(required = false, value = "file") MultipartFile file) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        //上传图片
        String path = UUID.randomUUID().toString();
        Suggestion suggestion = new Suggestion();
        if (null != file && !file.isEmpty()) {
            String filename = file.getOriginalFilename();
            assert filename != null;
            if (filename.endsWith(".jpg") || filename.endsWith(".JPG")
                    || filename.endsWith(".png") || filename.endsWith(".PNG")
                    || filename.endsWith(".jpeg") || filename.endsWith(".JPEG")) {
                //文件上传
                FileUtils.copyInputStreamToFile(file.getInputStream(),
                        new File(request.getServletContext().getRealPath("/picture/suggestion/"), path + ".jpg"));

                Thumbnails.of(request.getServletContext().getRealPath("/picture/suggestion" + "/" + path + ".jpg"))
                        .scale(0.4f).toFile(request.getServletContext().getRealPath("/picture/suggestion" + "/" + path + ".jpg"));
                suggestion.setImagePath("/picture/suggestion" + "/" + path + ".jpg");
            } else {
                return new RequestResult<>(StatEnum.FILE_FORMAT_ERROR, null);
            }
        } else {
            suggestion.setImagePath("");
        }
        suggestion.setUser(user);
        suggestion.setDescription(description);
        return suggestionService.addSuggestion(suggestion);
    }

    @PostMapping("/show")
    public RequestResult showSuggestion(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() == 0) {
            return new RequestResult(StatEnum.NOT_HAVE_POWER);
        }
        return suggestionService.show();
    }
}
