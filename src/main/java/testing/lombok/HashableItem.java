// Copyright (c) 2017 Boomi, Inc.
package testing.lombok;/**
 * Created by rick on 11/7/17.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Required;

import java.util.Date;

/**
 * @author rick.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class HashableItem {
    @NonNull String name;
    String last;
    Date birthday;
    @NonNull int id;
}
