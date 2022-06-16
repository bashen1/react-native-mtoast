#import "Mtoast.h"

@implementation Mtoast

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show:(NSDictionary *)props) {
    
    NSString *title = [props objectForKey: @"title"];
    NSNumber *titleSize = [props objectForKey: @"titleSize"];
    NSString *titleColor = [props objectForKey: @"titleColor"];
    
    NSNumber *duration = [props objectForKey: @"duration"];
    NSString *tintColor = [props objectForKey: @"tintColor"];
    
    NSString *position = [props objectForKey: @"position"];
    
    CSToastStyle *style = [[CSToastStyle alloc] initWithDefaultStyle];
    
    if (tintColor != nil && [tintColor length] > 0) {
        style.backgroundColor = [self colorFromHexCode: tintColor];
    }
    if (titleColor != nil && [titleColor length] > 0) {
        style.messageColor = [self colorFromHexCode: titleColor];
    }
    if (titleSize != nil && ![titleSize isEqual:@0]) {
        style.messageFont = [UIFont systemFontOfSize: [titleSize intValue]];
    }

    if(duration != nil) {
        duration = duration.intValue == 0 ? [NSNumber numberWithFloat:1.0] : [NSNumber numberWithFloat:3.0];
    }
    
    const NSString *toastPosition = [self getPosition: position];
    
    UIWindow *window = [[UIApplication sharedApplication] keyWindow];

    // toast with all possible options
    [window
     makeToast: title
     duration: duration.floatValue
     position: toastPosition
     title: nil
     image: nil
     style: style
     completion:^(BOOL didTap) {
      if (didTap) {
          NSLog(@"completion from tap");
      } else {
          NSLog(@"completion without tap");
      }
    }];
}

- (const NSString *__strong) getPosition: (NSString *)position {
    if([position isEqualToString:@"top"]) {
        return CSToastPositionTop;
    } else if([position isEqualToString:@"center"]) {
        return CSToastPositionCenter;
    } else {
        return CSToastPositionBottom;
    }
}

- (UIColor *) colorFromHexCode:(NSString *)hexString {
    NSString *cleanString = [hexString stringByReplacingOccurrencesOfString:@"#" withString:@""];
    if([cleanString length] == 3) {
        cleanString = [NSString stringWithFormat:@"%@%@%@%@%@%@",
                       [cleanString substringWithRange:NSMakeRange(0, 1)],[cleanString substringWithRange:NSMakeRange(0, 1)],
                       [cleanString substringWithRange:NSMakeRange(1, 1)],[cleanString substringWithRange:NSMakeRange(1, 1)],
                       [cleanString substringWithRange:NSMakeRange(2, 1)],[cleanString substringWithRange:NSMakeRange(2, 1)]];
    }
    if([cleanString length] == 6) {
        cleanString = [cleanString stringByAppendingString:@"ff"];
    }
    
    unsigned int baseValue;
    [[NSScanner scannerWithString:cleanString] scanHexInt:&baseValue];
    
    float red = ((baseValue >> 24) & 0xFF)/255.0f;
    float green = ((baseValue >> 16) & 0xFF)/255.0f;
    float blue = ((baseValue >> 8) & 0xFF)/255.0f;
    float alpha = ((baseValue >> 0) & 0xFF)/255.0f;
    
    return [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
}

@end
