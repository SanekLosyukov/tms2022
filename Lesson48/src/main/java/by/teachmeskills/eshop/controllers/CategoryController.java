package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.PagesPathEnum.UPLOAD_PAGE;

@Validated
@Tag(name = "category", description = "The Category API")
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ModelAndView openCategoryPage(@PathVariable int id) throws Exception {
        return categoryService.getCategoryData(id);
    }

    @GetMapping("/upload")
    public ModelAndView openUploadCategoriesPage() {
        return new ModelAndView(UPLOAD_PAGE.getPath());
    }

    @Operation(
            summary = "Find all categories",
            description = "Find all existed categories in Eshop",
            tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All categories were found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Categories not fount - forbidden operation"
            )
    })
    @Parameter(name = "name", description = "Name of category", required = true)
    @GetMapping("/all/{id}")
    public ResponseEntity<List<CategoryDto>> getAllCategories(@Min(value = 5, message = "Min value is 5") @PathVariable String id) {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) throws Exception {
        CategoryDto categoryDto = categoryService.getCertainCategory(id);
        if (Optional.ofNullable(categoryDto).isPresent()) {
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto created = categoryService.createCategory(categoryDto);
        if (Optional.ofNullable(created).isPresent()) {
            return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<List<CategoryDto>> createCategory(@RequestParam("file") MultipartFile file) throws Exception {
        return new ResponseEntity<>(categoryService.saveCategoriesFromFile(file), HttpStatus.CREATED);
    }
}
