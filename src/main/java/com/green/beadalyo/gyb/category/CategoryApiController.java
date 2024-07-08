package com.green.beadalyo.gyb.category;

import com.green.beadalyo.gyb.common.*;
import com.green.beadalyo.gyb.model.Category;
import com.green.beadalyo.gyb.model.User;
import com.green.beadalyo.gyb.response.CategoryRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryApiController
{

    private final CategoryService service;

    @PutMapping
    @Operation(summary = "카테고리 추가")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultDto.class)),
            description =
                    "<p> 1 : 정상 </p>" +
                    "<p> -1 : 실패 </p>" +
                    "<p> -2 : 로그인 정보 획득 실패 </p>" +
                    "<p> -3 : 권한 부족 </p>" +
                    "<p> -4 : 파일 확장자 체크 실패(jpg, png, gif) </p>"
    )
    public Result putCategory(@RequestPart String str, @RequestPart MultipartFile file)
    {
        User user = new User().Admin();
        //유효성 검증
        if (user == null)
            return ResultError.builder().statusCode(-2).resultMsg("유저 정보가 일치하지 않습니다.").build();
        //관리자 권한 검증
        if (!user.getUserRole().equals("ROLE_ADMIN"))
            return ResultError.builder().statusCode(-3).resultMsg("권한이 부족합니다.").build();

        String filename = null ;
        if (file != null && !file.isEmpty())
        {
            //파일 확장자 유효성 검증
            try {
                if (!FileUtils.checksumExt(List.of(Ext.GIF,Ext.PNG,Ext.JPG,Ext.JPEG) ,file))
                    return ResultError.builder().statusCode(-4).resultMsg("파일은 jpg, gif, png 만 허용됩니다.").build();

                filename = FileUtils.fileInput("category",file) ;
            } catch (Exception e) {
                log.error("An error occurred: ", e);
                return ResultError.builder().build();
            }

        }

        try {
            service.InsertCategory(str, filename);
        } catch (DuplicateKeyException e) {
            try {
                FileUtils.fileDelete(filename) ;
            } catch (Exception e1) {log.error(e1.toString());}
            return ResultError.builder().statusCode(-5).resultMsg("이미 만들어져있는 카테고리 입니다.").build() ;
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            try {
                FileUtils.fileDelete(filename) ;
            } catch (Exception e1) {log.error(e1.toString());}
            return ResultError.builder().build();
        }

        return ResultDto.builder().build();

    }
    
    @DeleteMapping("{seq}")
    @Operation(summary = "카테고리 삭제")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultDto.class)),
            description =
                    "<p> 1 : 정상 </p>" +
                            "<p> -1 : 실패 </p>" +
                            "<p> -2 : 로그인 정보 획득 실패 </p>" +
                            "<p> -3 : 권한 부족 </p>" +
                            "<p> -4 : 존재하지 않는 카테고리 </p>"
    )
    public Result deleteCategory(@PathVariable Long seq)
    {
        User user = new User().Admin();
        //유효성 검증
        if (user == null)
            return ResultError.builder().statusCode(-2).resultMsg("유저 정보가 일치하지 않습니다.").build();
        //관리자 권한 검증
        if (!user.getUserRole().equals("ROLE_ADMIN"))
            return ResultError.builder().statusCode(-3).resultMsg("권한이 부족합니다.").build();

        try {
            Category cate = service.getCategory(seq) ;
            FileUtils.fileDelete(cate.getCategoryPic()) ;
            service.deleteCategory(cate) ;
            
            return ResultDto.builder().build();
        } catch (NullPointerException e) {
            return ResultError.builder().statusCode(-4).resultMsg("존재하지 않는 카테고리입니다.").build();
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
    }

    @GetMapping
    @Operation(summary = "카테고리 리스트 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ResultDto.class)),
            description =
                    "<p> 1 : 정상 </p>" +
                    "<p> -1 : 실패 </p>"
    )
    public Result getCategoryList()
    {
        try {
            List<Category> list = service.getCategoryAll() ;
            List<CategoryRes> reslist = CategoryRes.toCategoryRes(list) ;
            return ResultDto.builder().resultData(reslist).build() ;
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
    }


}