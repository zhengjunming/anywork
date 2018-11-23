package com.qg.anywork.service.impl;

import com.qg.anywork.dao.ChapterDao;
import com.qg.anywork.dao.OrganizationDao;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.organization.OrganizationException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Chapter;
import com.qg.anywork.model.po.Organization;
import com.qg.anywork.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by ming on 18-8-5 下午10:15
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public RequestResult<List<Chapter>> getByOrganizationId(int organizationId) {
        if (organizationDao.getById(organizationId) == null) {
            throw new OrganizationException(StatEnum.ORGANIZATION_NOT_EXIST);
        }
        Organization organization = organizationDao.getById(organizationId);
        List<Chapter> chapters = chapterDao.getByUserId(organization.getTeacherId());
        for (Chapter chapter : chapters) {
            chapter.setOrganizationId(organizationId);
        }
        return new RequestResult<>(1, "获取成功", chapters);
    }

    @Override
    public RequestResult addChapter(int userId, Chapter chapter) {
        chapter.setUserId(userId);
        if (chapter.getChapterName().length() > 32) {
            throw new OrganizationException(StatEnum.CHAPTER_TOO_LONG);
        }
        chapterDao.addChapter(chapter);
        return new RequestResult<>(1, "添加成功", chapter);
    }

    @Override
    public RequestResult deleteChapter(int chapterId) {
        chapterDao.deleteChapter(chapterId);
        return new RequestResult(1, "删除成功");
    }

    @Override
    public RequestResult updateChapter(int chapterId, String chapterName) {
        if (chapterName.length() > 32) {
            throw new OrganizationException(StatEnum.CHAPTER_TOO_LONG);
        }
        Chapter chapter = new Chapter();
        chapter.setChapterId(chapterId);
        chapter.setChapterName(chapterName);
        chapterDao.updateChapter(chapter);
        return new RequestResult<>(1, "修改成功", chapter);
    }
}
