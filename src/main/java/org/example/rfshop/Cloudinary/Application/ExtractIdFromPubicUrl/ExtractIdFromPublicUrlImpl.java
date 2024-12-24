package org.example.rfshop.Cloudinary.Application.ExtractIdFromPubicUrl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExtractIdFromPublicUrlImpl  implements  ExtractIdFromPublicUrl{

    @Override
    public Optional<String> execute(String publicId) {
         List<String> divider = List.of(publicId.split("/"));
         return divider.getLast().split("\\.")[0].describeConstable();
    }
}
