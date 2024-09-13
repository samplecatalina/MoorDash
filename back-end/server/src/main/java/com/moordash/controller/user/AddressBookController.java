package com.moordash.controller.user;

import com.moordash.context.BaseContext;
import com.moordash.entity.AddressBook;
import com.moordash.result.Result;
import com.moordash.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@Api(tags = "Customer-side address book interface")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * Query all address information of the currently logged-in user
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Query all address information of the currently logged-in user")
    public Result<List<AddressBook>> list() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        List<AddressBook> list = addressBookService.list(addressBook);
        return Result.success(list);
    }

    /**
     * Add new address
     *
     * @param addressBook
     * @return
     */
    @PostMapping
    @ApiOperation("Add new address")
    public Result save(@RequestBody AddressBook addressBook) {
        addressBookService.save(addressBook);
        return Result.success();
    }

    /**
     * Query address by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Query address by id")
    public Result<AddressBook> getById(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * Update address by id
     *
     * @param addressBook
     * @return
     */
    @PutMapping
    @ApiOperation("Update address by id")
    public Result update(@RequestBody AddressBook addressBook) {
        addressBookService.update(addressBook);
        return Result.success();
    }

    /**
     * Set default address
     *
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    @ApiOperation("Set default address")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    /**
     * Delete address by id
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete address by id")
    public Result deleteById(Long id) {
        addressBookService.deleteById(id);
        return Result.success();
    }
    /**
     * Query default address
     */
    @GetMapping("default")
    @ApiOperation("Query default address")
    public Result<AddressBook> getDefault() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(1);
        List<AddressBook> list = addressBookService.list(addressBook);

        if (list != null && list.size() > 0) {
            return Result.success(list.get(0));
        }

        return Result.error("Default address not found");
    }

}
